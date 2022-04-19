
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"

using namespace llvm;

namespace
{

  class ExamplePass : public ModulePass
  {

  public:
    static char ID;
    ExamplePass() : ModulePass(ID) {}

    bool doInitialization(Module &M) override;
    bool runOnModule(Module &M) override;
  };

} // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M)
{

  return true;
}

bool ExamplePass::runOnModule(Module &M)
{

  IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());
  Type *CharTy = Type::getInt8PtrTy(M.getContext());
  Type *VoidTy = Type::getVoidTy(M.getContext());

  FunctionType *debugFnTy = FunctionType::get(VoidTy, {Int32Ty}, false);
  FunctionCallee debugFn = M.getOrInsertFunction("debug", debugFnTy);

  errs() << "runOnModule\n";

  for (Function &F : M)
  {

    /* add you code here */
    errs() << F.getName() << "\n";
    if (F.getName() == "main")
    {

      BasicBlock &BBEntry = F.getEntryBlock();

      BasicBlock::iterator IP = BBEntry.getFirstInsertionPt();
      IRBuilder<> IRB(&(*IP));
      IRB.CreateCall(debugFn, ConstantInt::get(Int32Ty, 9527));

      F.getArg(0)->replaceAllUsesWith(ConstantInt::get(Int32Ty, 9487));

      Value *Argv1 = IRB.CreateGlobalStringPtr("aesophor is ghost !!!");
      IRB.CreateStore(Argv1, IRB.CreateGEP(CharTy, F.getArg(1), ConstantInt::get(Int32Ty, 1)));
    }
  }

  return true;
}

static void registerExamplePass(const PassManagerBuilder &,
                                legacy::PassManagerBase &PM)
{

  PM.add(new ExamplePass());
}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);
